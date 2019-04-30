package tech.lacambra.blog.cli;

import org.tomitribe.crest.Main;
import org.tomitribe.crest.api.Command;
import org.tomitribe.crest.api.Option;
import org.tomitribe.crest.environments.SystemEnvironment;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class Cli {

  private static final Logger LOGGER = Logger.getLogger(Cli.class.getName());

  public static void main(String[] args) throws Exception {
    Main main = new Main(Cli.class);
    main.main(new SystemEnvironment(), args);
  }

  @Command("ls")
  public void ls(@Option("a") boolean all, @Option("h") boolean human, @Option("l") boolean list, URI path) {
    String cmd = "ls";

    cmd = setArg(cmd, "a", all);
    cmd = setArg(cmd, "h", human);
    cmd = setArg(cmd, "l", list);

    cmd += " " + path.toString();

    executeCommand(cmd, System.out::println, System.err::println).join();
  }

  public CompletableFuture<Process> executeCommand(String command, Consumer<String> stdOutConsumer, Consumer<String> errConsumer) {

    Process p;

    try {
      p = Runtime.getRuntime().exec(command);
      run(p::getInputStream, stdOutConsumer);
      run(p::getErrorStream, errConsumer);
      return p.onExit();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void run(Supplier<InputStream> streamSupplier, Consumer<String> streamConsumer) {
    try (InputStream stream = streamSupplier.get()) {
      StringBuilder currentLine = new StringBuilder();
      int nextChar;
      while ((nextChar = stream.read()) != -1) {
        if (nextChar == '\n') {
          streamConsumer.accept(currentLine.toString());
          currentLine.setLength(0);
          continue;
        }
        currentLine.append((char) nextChar);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String setArg(String command, String name, boolean set) {
    return set ? command + " -" + name : command;
  }

}
