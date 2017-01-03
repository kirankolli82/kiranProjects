import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Kiran Kolli on 22-12-2016.
 */
public class Test {
    public static void main(String[] args) throws URISyntaxException {
        Path path = Paths.get(Test.class.getClassLoader().getResource("Test.class").toURI());
        System.out.println(path.getFileName().toString());
        System.out.println(path.getRoot().toString());
        for (File root : File.listRoots()) {
            //System.out.println(root.toPath().toString());
            System.out.println(root.getPath());
        }
    }
}
