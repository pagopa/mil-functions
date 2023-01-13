data "azurerm_cosmosdb_account" "mil" {
  name                = "${local.project}-cosmos"
  resource_group_name = "${local.project}-data-rg"
}

data "azurerm_resource_group_template_deployment" "cae" {
  name                = "${local.project}-cae"
  resource_group_name = "${local.project}-app-rg"
}