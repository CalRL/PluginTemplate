package me.calrl.plugintemplate.commands;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import me.calrl.plugintemplate.utils.Result;
import org.bukkit.command.CommandSender;

/** Abstract base for command nodes in a tree-based command system. */
public abstract class CommandNode {

  protected final String identifier;
  protected final Map<String, CommandNode> children = new HashMap<>();

  /** Constructs a CommandNode.
   *
   * @param identifier the command identifier
   */
  public CommandNode(String identifier) {
    this.identifier = identifier;
  }

  /** Adds a child command node.
   *
   * @param identifier the child's identifier
   * @param child the child node
   */
  public void addChild(String identifier, CommandNode child) {
    this.children.put(identifier.toLowerCase(), child);
  }

  /** Checks if the input matches this node's identifier.
   *
   * @param input the input to test
   * @return true if the input matches case-insensitively
   */
  public boolean matches(String input) {
    return this.identifier.equalsIgnoreCase(input);
  }

  /** Executes this command.
   *
   * @param sender the command sender
   * @param args the arguments
   * @param depth the current depth in the command tree
   * @return the result of execution
   */
  public abstract Result execute(CommandSender sender, String[] args, int depth);

  /** Delegates execution to a matching child if present.
   *
   * @param sender the command sender
   * @param args the arguments
   * @param depth the current depth in the command tree
   * @return the result, or {@code Result.NO_CHILD} if no child matches
   */
  public Result executeIfChildPresent(CommandSender sender, String[] args, int depth) {
    if (args.length > depth) {
      CommandNode node = this.children.get(args[depth].toLowerCase());
      if (node != null) {
        return node.execute(sender, args, depth + 1);
      }
    }
    return Result.NO_CHILD;
  }

  /** Provides tab completion suggestions.
   *
   * @param sender the command sender
   * @param args the arguments
   * @param depth the current depth in the command tree
   * @return list of matching completions
   */
  public List<String> tabComplete(CommandSender sender, String[] args, int depth) {
    if (args.length == depth + 1) {
      return children.keySet().stream()
          .filter(key -> key.startsWith(args[depth].toLowerCase()))
          .collect(Collectors.toList());
    }

    String next = args[depth].toLowerCase();
    CommandNode child = children.get(next);
    if (child != null) {
      return child.tabComplete(sender, args, depth + 1);
    }

    return Collections.emptyList();
  }

  /** Returns the identifier of this node.
   *
   * @return the identifier
   */
  public String getIdentifier() {
    return this.identifier;
  }

  /** Returns the children of this node.
   *
   * @return the map of child identifiers to nodes
   */
  public Map<String, CommandNode> getChildren() {
    return children;
  }
}
