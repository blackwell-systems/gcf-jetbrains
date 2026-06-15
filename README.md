# GCF - Graph Compact Format (JetBrains Plugin)

Syntax highlighting for [GCF](https://gcformat.com), the AI-native wire format for structured data. 71% fewer tokens than JSON.

## Features

- Syntax highlighting for `.gcf` files via TextMate grammar
- Highlights headers, section markers, symbol IDs, edge notation, strings, numbers, booleans, key-value pairs, and more
- Works in all JetBrains IDEs: IntelliJ IDEA, PyCharm, WebStorm, GoLand, RustRover, CLion, and others

## Installation

### From disk (local build)

1. Build the plugin:

   ```
   ./gradlew buildPlugin
   ```

2. The plugin zip will be at `build/distributions/gcf-jetbrains-0.1.0.zip`

3. In your JetBrains IDE, go to **Settings > Plugins > Gear icon > Install Plugin from Disk...**

4. Select the zip file and restart the IDE.

### From JetBrains Marketplace

Coming soon.

## Requirements

- JetBrains IDE version 2023.1 or later
- The bundled TextMate plugin must be enabled (it is enabled by default)

## What is GCF?

GCF (Graph Compact Format) is a wire format designed for LLM tool responses, MCP servers, and agent-to-agent communication. It supports five output formats (JSON, YAML, CSV, TOML, MessagePack) with perfect round-trip fidelity.

- [GCF Specification](https://github.com/blackwell-systems/gcf)
- [Documentation](https://gcformat.com)
- [VS Code Extension](https://marketplace.visualstudio.com/items?itemName=blackwell-systems.gcf-vscode)

## Development

### Prerequisites

- JDK 17+
- Gradle (wrapper included)

### Build

```
./gradlew buildPlugin
```

### Run in a sandbox IDE

```
./gradlew runIde
```

This launches a sandboxed IntelliJ IDEA instance with the plugin installed for testing.

### Verify

```
./gradlew verifyPlugin
```

## License

MIT. See [LICENSE](LICENSE).
