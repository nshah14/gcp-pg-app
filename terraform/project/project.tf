data "google_billing_account" "acct" {
  display_name = "My Billing Account"
  open         = true
}
resource "google_project" "tf_project" {
  name       = var.name
  project_id = var.project_id
  # org_id     = "1234567"
  billing_account = data.google_billing_account.acct.id
}

resource "google_project_iam_member" "project" {
  project = var.project_id
  role    = "roles/editor"
  member  = "user:${var.member}"

}

resource "google_service_account" "sa-editor" {

  account_id   = "${var.service_account_editor}-${var.env}-sa" 
  display_name = var.service_account
  project = var.name
}
resource "google_service_account" "sa-admin" {

  account_id   = "${var.service_account_admin}-${var.env}-sa" 
  display_name = var.service_account
  project = var.name
}

resource "google_service_account_key" "sa-editor" {
  service_account_id = google_service_account.sa-editor.account_id
  public_key_type    = "TYPE_X509_PEM_FILE"
}


output "service_account_key" {
  value = google_service_account_key.sa-editor.private_key
}

resource "google_project_iam_member" "appengine" {
  project = var.project_id
  role    = "roles/editor"
  member = "serviceAccount:${var.service_account_admin}-${var.env}-sa@${var.project_id}.iam.gserviceaccount.com"
}


resource "google_app_engine_application" "app" {
  project     = google_project.tf_project.project_id
  location_id = "us-central"
}

