package com.example.openov.Utilz;

// Dit is de distributable van de database settings, deze dus niet aanpassen.
// Copieeer de inhoud en maak een neiuwe file aan genaamd DbSettings in dezelfde map.

public class DbSettingsDist
{
    // database host, default is meestal localhost of 127.0.0.1
    public static String db_host = "";

    // Gebruikersnaam van je database configuratie, meestal root
    public static String db_user = "";

    // Wachtwoord van je database configuratie meestal leeg, aanpassen indien nodig.
    public static String db_pass = "";

    // Naam van de database die je hebt aangemaakt voorbeeld, openov
    public static String db_name = "";

    // 3306 is de standaard database port van xampp mysql, aanpassen indien nodig.
    public static int db_port = 3306;
}
