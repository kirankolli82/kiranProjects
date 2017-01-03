package com.kiran.file.parsing;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kiran Kolli on 17-10-2016.
 */
public class MultiThreadedNioFileParser {

    private static final int TIMEOUT_IN_SECONDS = 20;
    private final File file;
    private final FileParsingConsumerFactory factory;
    private final int numberOfConsumers;
    private final LinkedBlockingQueue<StringBuilder> availableToRead = new LinkedBlockingQueue<>();
    private final LinkedBlockingQueue<StringBuilder> availableToToProcess = new LinkedBlockingQueue<>();
    private final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1500);
    private final StringBuilder lineOverFlow = new StringBuilder(1500);
    private final Charset charset = Charset.forName("UTF-8");

    public MultiThreadedNioFileParser(File file, FileParsingConsumerFactory factory, int numberOfConsumers) {
        this.file = file;
        this.factory = factory;
        this.numberOfConsumers = numberOfConsumers;
    }

    public void parse() throws IOException {
        for (; 0 < availableToRead.remainingCapacity(); ) {
            availableToRead.add(new StringBuilder(1500));
        }

        List<Thread> consumerThreads = new ArrayList<>();

        for (int index = 0; index < numberOfConsumers; index++) {
            Thread consumerThread = new Thread(new ConsumerRunnable(factory.create()));
            consumerThreads.add(consumerThread);
            consumerThread.start();
        }

        try (FileChannel fc = (FileChannel) Files.newByteChannel(Paths.get(file.getAbsolutePath()))) {
            try {
                StringBuilder toRead = availableToRead.poll(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
                if (fc.read(byteBuffer) > 0) {
                    do {
                        byteBuffer.rewind();
                        toRead.append(charset.decode(byteBuffer));
                        while (readLine(toRead) != -1) {
                            availableToToProcess.put(toRead);
                            toRead = availableToRead.poll(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
                            toRead.append(lineOverFlow);
                            lineOverFlow.delete(0, lineOverFlow.length());
                        }

                        byteBuffer.flip();

                    } while (fc.read(byteBuffer) > 0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        consumerThreads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private int readLine(StringBuilder lineBeingRead) {
        int lineLength = lineBeingRead.indexOf(System.lineSeparator());
        if (lineLength != -1) {
            lineOverFlow.append(lineBeingRead.substring(lineLength + 1));
            lineBeingRead.delete(lineLength, lineBeingRead.length());
            return 1;
        }

        return -1;
    }

    private class ConsumerRunnable implements Runnable {
        private final FileParsingConsumer consumer;

        private ConsumerRunnable(FileParsingConsumer consumer) {
            this.consumer = consumer;
        }

        @Override
        public void run() {
            try {
                StringBuilder toProcess = availableToToProcess.poll(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
                do {
                    consumer.accept(toProcess);
                    toProcess.delete(0, toProcess.length());
                    availableToRead.put(toProcess);
                    toProcess = availableToToProcess.poll(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
                } while (toProcess != null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
