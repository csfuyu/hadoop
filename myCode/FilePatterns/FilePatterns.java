import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.PathFilter;
import java.io.InputStream;
import java.net.URI;

class RegexExcludeFilter implements PathFilter{
private final String regex;

public RegexExcludeFilter(String regex){
this.regex = regex;
}

public boolean accept(Path path){
return !path.toString().matches(regex);
}

}

public class FilePatterns{
public static void main(String[] args) throws Exception {
Configuration conf = new Configuration();
FileSystem fs = FileSystem.get(URI.create("/tmp"), conf);
FileStatus[] status = fs.globStatus(new Path("/user/hadoop/input/metoffice/*"), new RegexExcludeFilter("^.*/user/hadoop/input/metoffice/s.*txt$"));
for(FileStatus nfs: status){
System.out.println(nfs.getPath());
}
}
}
