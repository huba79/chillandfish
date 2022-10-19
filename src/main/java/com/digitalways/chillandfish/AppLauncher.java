package com.digitalways.chillandfish;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.Serial;

/**
 * @author huba
 */
@SpringBootApplication
@EnableJpaRepositories("com.digitalways.chillandfish")
public class AppLauncher implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws RuntimeException {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws RuntimeException {
        try {
            new SpringApplication(AppLauncher.class).run(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    static class ExitException extends RuntimeException implements ExitCodeGenerator {
        @Serial
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}


