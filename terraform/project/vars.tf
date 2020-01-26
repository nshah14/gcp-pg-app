variable "name" {
  default = "nav-tf-gcp"
  type    = string
}
variable "project_id" {
  default = "nav-tf-gcp"
  type    = string
}
variable "env" {
  default = "dev"
  type    = string
  
}
variable "member"{
  default = ""
  type = string
}

variable "service_account_editor"{
  default = "editor"
  type    = string
}
variable "service_account_admin"{
  default = "admin"
  type    = string
}
variable "service_account"{
  default = "Editor Service account"
  type    = string
}
variable "app_name"{
  default = ""
  type    = string
}

variable "region"{
   default = "us-central1"
   type    = string
}
 
 variable "type_tier"{
   default = "db-f1-micro"
   type    = string
}
variable "disk_size"{
  default = "10"
  type = string

}
variable "password"{
  default = "changeme"
  type = string
}