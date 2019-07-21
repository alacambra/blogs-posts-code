package tech.lacambra.blog.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class GreetingController {

  @Autowired
  private GreetingRepository greetingRepository;

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/greeting")
  public List<Greeting> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return StreamSupport.stream(greetingRepository.findAll().spliterator(), false).collect(Collectors.toList());
  }

  @PostMapping("/greeting")
  public Greeting saveGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
    return greetingRepository.save(greeting);
  }
}