
resource "google_app_engine_standard_app_version" "rev_sb_pg_v1"{
  version_id = "v1"
  runtime    = "java11"
  project    = var.project_id

  entrypoint {
    shell = "mvn package appengine:deploy"
  }

  deployment{
    zip {
      source_url = "https://storage.googleapis.com/${var.project_id}.appspot.com/gcp-pg-app-master"
    }
  }

  env_variables = {
    port = "8080"
  }

  delete_service_on_destroy = true
}
