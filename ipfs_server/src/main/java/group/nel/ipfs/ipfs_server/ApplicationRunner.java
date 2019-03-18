package group.nel.ipfs.ipfs_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan("group.nel.ipfs.controller")
public class ApplicationRunner 
{
    public static void main( String[] args )
    {
        System.out.println( "Start to run ifps_server" );
        SpringApplication.run(ApplicationRunner.class, args);
    }
}
