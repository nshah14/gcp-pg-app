resource "google_sql_database_instance" "master" {
  name             = "${var.env}-pg-instance"
  database_version = "POSTGRES_11"
  region           =  var.region
  project          =  var.project_id

  settings {
    tier              = var.type_tier
    activation_policy = "ALWAYS"
    disk_size         = var.disk_size
  }
}

resource "random_id" "rev" {
  byte_length = 4
}

resource "google_sql_user" "users" {
  name     = "postgres"
  instance = google_sql_database_instance.master.name
  host     = "${var.project_id}.com"
  password = var.password
  project  =  var.project_id
}

output "google_sql_user" {
  value = google_sql_user.users.name
}
output "google_sql_password" {
  value = google_sql_user.users.password
}