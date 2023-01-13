#
# General variables definition.
#
variable "prefix" {
  type = string
  validation {
    condition = (
      length(var.prefix) <= 6
    )
    error_message = "Max length is 6 chars."
  }
}

variable "env" {
  type = string
  validation {
    condition = (
      length(var.env) <= 4
    )
    error_message = "Max length is 4 chars."
  }
}

variable "env_short" {
  type = string
  validation {
    condition = (
      length(var.env_short) <= 1
    )
    error_message = "Max length is 1 chars."
  }
}

variable "location" {
  type    = string
}

#
# mil-functions specific.
#
variable "image" {
  type        = string
  description = "Image of mil-functions microservice."
}

variable "container_name" {
  type        = string
  description = "Container name for the image."
}

variable "quarkus_log_level" {
  type        = string
  description = "Log level for Quarkus platform."
  default     = "ERROR"
}

variable "app_log_level" {
  type        = string
  description = "Log level for application."
  default     = "DEBUG"
}

variable "mongo_connect_timeout" {
  type        = string
  description = "Mongo connect timeout."
  default     = "5s"
}

variable "mongo_read_timeout" {
  type        = string
  description = "Mongo read timeout."
  default     = "10s"
}

variable "mongo_server_selection_timeout" {
  type        = string
  description = "Mongo server selection timeout."
  default     = "5s"
}

variable "cpu" {
  type        = number
  description = "CPUs assigned to the container."
  default     = 0.5
}

variable "ephemeral_storage" {
  type        = string
  description = "Ephemeral storage assigned to the container."
  default     = "1.0Gi"
}

variable "memory" {
  type        = string
  description = "Memory assigned to the container."
  default     = "1.0Gi"
}

variable "max_replicas" {
  type        = number
  description = "Max number of replicas."
  default     = 5
}

variable "min_replicas" {
  type        = number
  description = "Min number of replicas."
  default     = 0
}