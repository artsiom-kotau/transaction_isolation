docker run -e POSTGRES_DB=store -e POSTGRES_USER=store_user -e POSTGRES_PASSWORD=store --publish=127.0.0.1:5432:5432 -d postgres