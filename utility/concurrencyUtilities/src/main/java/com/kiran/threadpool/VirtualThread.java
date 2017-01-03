package com.kiran.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by Kiran Kolli on 17-10-2016.
 */
public class VirtualThread<ResultTypeT> extends FutureTask<ResultTypeT> {

    public VirtualThread(Callable<ResultTypeT> callable) {
        super(callable);
    }


}
