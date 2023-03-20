Feature: Obtener lista de historias por Id
  yo como usuario de la API de marvel
  quiero listar historias de marvel por ID
  para poder visualizar la informacion de historia

  @HistoriasporId
  Scenario: Obtener lista de historias por id
    Given que soy un usuario de la API de marvel
    When solicito obtener historias filtrados por id
    Then la API retornara la historia por id


  Scenario Outline: obtener lista de historias por id
    Given que soy un usuario de las historias de marvel
    When solicito obtener las historias por id <id>
    Then retornara la historia por <id>

    Examples:
      | id       |
      | "1240"   |
      | "3457"   |
      | "1e9947" |