Feature: Reservar  asistencia de vuelo
  Yo como usuario de la página web despegar
  quiero ir a la página de asistencia de asistencias
  para reservar una asistencia con navegador

  Scenario: reserva de asistencia nacional para 4 personas
    Given que necesito asistencia nacional  para cuatro personas en "Chrome"
    When seleccione asistencia nacional e informacion requerida
    Then debo obtener un mensaje de reserva de asistencia exitosa

  Scenario: reserva de asistencia internacional para 4 personas en "Chrome"
    Given que necesito asistencia  para cuatro personas
    When seleccione asistencia internacional e informacion requerida
    Then debe observar un mensaje de reserva de asistencia exitosa