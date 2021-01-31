package spring.course.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.course.datajpa.models.service.IUploadFileService;

@SpringBootApplication
public class DataJpaApplication  implements CommandLineRunner {

    @Autowired
    IUploadFileService uploadService;

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        uploadService.deleteAll();
        uploadService.init();
    }
}
