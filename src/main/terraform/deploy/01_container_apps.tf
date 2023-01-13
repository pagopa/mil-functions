# Container App.
locals {
  name = "${local.project}-init-ca"
}

resource "azurerm_resource_group_template_deployment" "init_ca" {
  name                = local.name
  resource_group_name = "${local.project}-app-rg"
  deployment_mode     = "Incremental"

  template_content = templatefile("templates/init-ca.json",
    {
      name                           = local.name,
      location                       = var.location,
      
      mongo_connection_string_1      = data.azurerm_cosmosdb_account.mil.connection_strings[0],
      mongo_connection_string_2      = data.azurerm_cosmosdb_account.mil.connection_strings[1],
      
      managed_environment_id         = data.azurerm_resource_group_template_deployment.cae.id,
      
      image                          = var.image,
      container_name                 = var.container_name,
      
      quarkus_log_level              = var.quarkus_log_level,
      app_log_level                  = var.app_log_level,
      mongo_connect_timeout          = var.mongo_connect_timeout,
      mongo_read_timeout             = var.mongo_read_timeout,
      mongo_server_selection_timeout = var.mongo_server_selection_timeout,
      cpu                            = var.cpu,
      ephemeral_storage              = var.ephemeral_storage,
      memory                         = var.memory,
      max_replicas                   = var.max_replicas,
      min_replicas                   = var.min_replicas
    }
  )
}