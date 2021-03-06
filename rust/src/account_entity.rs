use uuid::Uuid;

use crate::datetime::Datetime;

pub struct AccountEntity {
    pub id: Uuid,
    pub created: Datetime,
    pub updated: Datetime,
    pub username: String,
    pub email: String,
    pub password_hash: Vec<u8>,
    pub password_salt: Vec<u8>,
    pub status: Status,
}

#[derive(sqlx::Type)]
#[sqlx(type_name = "ACCOUNT_STATUS", rename_all = "lowercase")]
pub enum Status {
    Unverified,
    Active,
    Disabled,
    Deleted,
}
