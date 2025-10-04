const express = require('express');
const fs = require('fs');
const app = express();
const port = 8080;

app.use(express.json());

const filepath = 'rentals.json';
let rentalsJSON;

//Comprovar si el fitxer JSON existeix
if (!fs.existsSync(filepath)) {
    // Crear document inicial
    rentalsJSON = { "rentals": [] };
    fs.writeFileSync(filepath, JSON.stringify(rentalsJSON, null, 2));
} else {
    // Llegir document existent
    const rawData = fs.readFileSync(filepath);
    rentalsJSON = JSON.parse(rawData);
}

// Endpoint POST: registrar un nou lloguer
app.post('/rentals', (req, res) => {
    const { maker, model, days, units } = req.body;

    //Si falta algun parametre --> Error
    if (!maker || !model || typeof days !== 'number' || typeof units !== 'number') {
        return res.status(400).json({ error: "Payload invàlid" });
    }

    const rental = {
        id: Date.now().toString(),
        maker,
        model,
        days,
        units,
        //Aqui seria millor "new Date().toISOString()", però per llegibilitat poso el que hi ha
        createdAt: new Date().toLocaleString()     
    };

    rentalsJSON['rentals'].push(rental);

    fs.writeFileSync(filepath, JSON.stringify(rentalsJSON, null, 2));

    res.status(201).json(rental);
});

// Endpoint GET: retornar tots els lloguers
app.get('/list', (req, res) => {
    res.json(rentalsJSON);
});

// Iniciar servidor
app.listen(port, () => {
    console.log(`Servidor HTTP escoltant a http://localhost:${port}`);
});
