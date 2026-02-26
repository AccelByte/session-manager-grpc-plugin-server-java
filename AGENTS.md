# session-manager-grpc-plugin-server-java

An Extend Override app for the **session manager** written in Java. AGS calls this gRPC server with lifecycle hooks whenever game sessions or party sessions are created, updated, or deleted.

This is a template project — clone it, replace the sample logic in the service implementation, and deploy.

## Build & Test

```bash
./gradlew build                      # Build with Gradle
./gradlew test                       # Run tests
docker compose up --build            # Run locally with Docker
./gradlew generateProto              # Regenerate proto code
```

## Architecture

AGS invokes this app's gRPC methods instead of its default logic:

```
Game Client → AGS → [gRPC] → This App → Response → AGS
```

The sample implementation handles six lifecycle hooks (OnSessionCreated, OnSessionUpdated, OnSessionDeleted, OnPartyCreated, OnPartyUpdated, OnPartyDeleted) and demonstrates injecting custom attributes into session data on creation events.

### Key Files

| Path | Purpose |
|---|---|
| `src/main/java/net/accelbyte/session/sessionmanager/Application.java` | Entry point — starts gRPC server, wires interceptors and observability |
| `src/main/java/net/accelbyte/session/sessionmanager/service/SessionManagerImplementation.java` | **Service implementation** — your custom logic goes here |
| `src/main/proto/session-manager.proto` | gRPC service definition (AccelByte-provided, do not modify) |
| `docker-compose.yaml` | Local development setup |
| `.env.template` | Environment variable template |

## Rules

See `.agents/rules/` for coding conventions, commit standards, and proto file policies.

## Environment

Copy `.env.template` to `.env` and fill in your credentials.

| Variable | Description |
|---|---|
| `AB_BASE_URL` | AccelByte base URL (e.g. `https://test.accelbyte.io`) |
| `AB_CLIENT_ID` | OAuth client ID |
| `AB_CLIENT_SECRET` | OAuth client secret |
| `AB_NAMESPACE` | Target namespace |
| `PLUGIN_GRPC_SERVER_AUTH_ENABLED` | Enable gRPC auth (`true` by default) |

## Dependencies

- [AccelByte Java SDK](https://github.com/AccelByte/accelbyte-java-sdk) (`net.accelbyte.sdk:sdk`) — AGS platform SDK and gRPC plugin utilities
