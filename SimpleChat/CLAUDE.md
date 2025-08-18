# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a simple multi-client chat application written in Java. The project demonstrates socket programming with a server-client architecture where multiple clients can connect to a single server and exchange messages in real-time.

## Architecture

The application consists of three main components:

- **Server.java**: Multi-threaded chat server that listens on port 12345 and manages multiple client connections using a `CopyOnWriteArrayList<ClientHandler>`. Each client connection is handled by a separate `ClientHandler` thread.
- **Client.java**: Chat client that connects to the server and handles both sending user input and receiving messages from other clients using separate threads.
- **Main.java**: Basic template file (currently contains IntelliJ IDEA demo code).

The server uses a broadcast mechanism where messages from one client are forwarded to all other connected clients. Client connections are managed through the inner `ClientHandler` class which implements `Runnable` for concurrent client handling.

## Development Commands

This is an IntelliJ IDEA project without Maven or Gradle build configuration.

### Compilation
```bash
javac -d out/production/SimpleChat src/*.java
```

### Running the Server
```bash
java -cp out/production/SimpleChat Server
```

### Running the Client
```bash
java -cp out/production/SimpleChat Client
```

### Running from IDE
Use IntelliJ IDEA's run configurations or press the run button next to the main methods.

## Key Technical Details

- Server port: 12345
- Client connects to localhost by default
- Uses standard Java I/O with `BufferedReader` and `PrintWriter`
- Thread-safe client list management with `CopyOnWriteArrayList`
- Graceful connection cleanup in `finally` blocks
- Korean language comments and messages in the source code