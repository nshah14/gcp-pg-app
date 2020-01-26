resource "google_storage_bucket" "tfstate"{
  name     = "${var.project_id}-tf-state"
  location = var.region
  project = var.project_id
}

output "tfstate_bucket_name" {
  value = google_storage_bucket.tfstate.name
}
terraform {
  backend "gcs" {
    bucket  = "nav-tf-gcp-pro-tf-state"
    prefix  = "terraform/state"
  }
}