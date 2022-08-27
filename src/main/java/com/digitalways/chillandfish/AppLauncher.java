package com.digitalways.chillandfish;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author huba
 */
@SpringBootApplication
//@EnableJpaRepositories
public class Chill2 implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws RuntimeException {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws RuntimeException {
        try {
            new SpringApplication(Chill2.class).run(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}

