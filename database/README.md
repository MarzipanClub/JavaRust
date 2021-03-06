# SQL

## Setup

### Postgres
Install Postgres.

    brew install postgresql

Create a database within postgres.

    createdb java_rust

To run migrations, you'll need to install `sqlx-cli`.

    cargo install sqlx-cli --no-default-features --features postgres

### Development

Run the following to create new up/down migration files making sure to give your file a descriptive name.

    DATABASE_URL="postgres://localhost:5432/java_rust" sqlx migrate add -r <migration_file_name>

When you need to run a migration locally.

    DATABASE_URL="postgres://localhost:5432/java_rust" cargo sqlx migrate run

Use this enable type checks against the database schema during development. Make sure to rerun whenever the schema changes.

    DATABASE_URL="postgres://localhost:5432/java_rust" cargo sqlx prepare

- This will update the `sqlx-data.json` which should match that of the production schema. Otherwise your local and production schemas are out of sync.

Use this to revert a migration.

    DATABASE_URL="postgres://localhost:5432/java_rust" cargo sqlx migrate revert
