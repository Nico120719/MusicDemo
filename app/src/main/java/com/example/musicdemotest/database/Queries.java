package com.example.musicdemotest.database;


import static com.example.musicdemotest.database.SampleDBHelper.*;


interface Queries {


    /* Requête de création de la table Samples */

    String DDL = "CREATE TABLE " + TABLE + " ("

            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "

            + NAME + " TEXT, " + CATEGORY + " TEXT, " + DESCRIPTION_FR + " TEXT, " + DESCRIPTION_EN + " TEXT, "

            + DUREE + " TEXT, " + COLLECTION + " BOOLEAN)";



    /* Insertion des données de la table */

    String INSERT = "INSERT INTO " + TABLE

            + " (" + NAME + ", " + CATEGORY + ", " + DESCRIPTION_FR + ", " + DESCRIPTION_EN + ", " + DUREE + ", " + COLLECTION + ")"

            + " VALUES " +

            "('drums.wav', 'drums', 'Échantillon de percussion lent style Trip Hop', 'Slow Trip Hop drum sample', '0:15', 0), " +

            "('industrialdrums.mp3', 'drums', 'Échantillon de percussion industrielle', 'Industrial drum sample', '0:11', 0), " +

            "('fastdrumsnare.wav', 'drums', 'Percussion Jazz tempo rapide', 'Fast tempo Jazz drum sample', '0:02', 0), " +

            "('horns.wav', 'horns', 'Échantillon de trompette Jazz lent', 'Slow Jazz trumpet sample', '0:13', 0), " +

            "('strings.wav', 'strings', 'Grattement de guitare accoustique', 'Accoustic guitar strum sample', '0:09', 0), " +

            "('synths.wav', 'synths', 'Clavier électronique Pop', 'Pop electronic synth sample', '0:09', 0), " +

            "('world.wav', 'world', 'Échantillon koto oriental', 'Oriental koto sample', '0:09', 0), " +

            "('cheer.wav', 'vocal', 'Foule en délire', 'Crowd cheering', '0:04', 0), " +

            "('darksynth.wav', 'synths', 'Synthétiseur futuriste', 'Futiristic synth sample', '0:03', 0), " +

            "('distordedsynth.wav', 'synths', 'Synthétiseur avec distortion style Techno', 'Techno-style distorded synth', '0:10', 0), " +

            "('harmony.wav', 'vocal', 'Chorale de voix féminines', 'Female choir', '0:10', 0), " +

            "('bluesguitar.wav', 'strings', 'Guitare accoutique de style Blues', 'Blues-style accoustic guitar', '0:06', 0), " +

            "('powerguitar.wav', 'strings', 'Guitare électrique style Rock & Roll', 'Rock & Roll-style electric guitar', '0:09', 0), " +

            "('sitar.wav', 'world', 'Sitar indienne', 'Indian sitar', '0:04', 0), " +

            "('marimba.wav', 'world', 'Marimba africaine', 'African marimba', '0:01', 0), " +

            "('flute.wav', 'world', 'Mélodie de flûte chinoise', 'Chinese melodic flute sample', '0:12', 0), " +

            "('saxophone.wav', 'horns', 'Mélodie de saxophone', 'Saxophone melody', '0:05', 0), " +

            "('frenchhorn.wav', 'horns', 'Cor d''orchestre symphonique', 'Symphonic orchestra french horn', '0:06', 0), " +

            "('scream.wav', 'vocal', 'Cri d''effroi', 'Fearful scream', '0:02', 0)";
}
