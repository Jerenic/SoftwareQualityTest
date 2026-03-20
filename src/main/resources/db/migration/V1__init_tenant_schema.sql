CREATE TABLE IF NOT EXISTS tenants
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(20) NOT NULL,
    api_key    VARCHAR(128),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_tenants_name ON tenants (name);
