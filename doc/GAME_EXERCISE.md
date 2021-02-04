# Simulation Lab Discussion

## Breakout with Inheritance

## Names and NetIDs

- Jiyang Tang, jt304
- Andre Wang, jw542
- Tinglong Zhu, tz88

### Menu

This superclass's purpose as an abstraction:

`Menu` uses a `VBox` to arrange several customized menu buttons.

```java
 public class Menu {

  public void makeButton(String buttonName);

  public void makeButtonFunction();

  public void displayButton();
}
```

#### Subclasses (the Open part)

This subclass's high-level behavorial differences from the superclass:

```java
 public class WelcomeMenu extends Menu {

  @Override
  public void makeButtonFunction();
}

public class NotificationMenu extends Menu {

  @Override
  public void makeButtonFunction();
}

public class InstructionMenu extends Menu {

  @Override
  public void makeButtonFunction();
}
```
