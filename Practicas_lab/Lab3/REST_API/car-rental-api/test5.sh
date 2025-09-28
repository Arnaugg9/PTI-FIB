#Crea el primer lloguer
curl -i -H "Content-Type: application/json" \
     -d '{"maker":"Seat","model":"Ibiza","days":3,"units":1}' \
     http://localhost:8080/rentals

sleep 2	# Fa pausa per tenir diferent temps

#Crea el segon lloguer
curl -i -H "Content-Type: application/json" \
     -d '{"maker":"Toyota","model":"Corolla","days":5,"units":2}' \
     http://localhost:8080/rentals
sleep 2	# Fa pausa per tenir diferent temps

#Crea el tercer lloguer
curl -i -H "Content-Type: application/json" \
     -d '{"maker":"Volkswagen","model":"Golf","days":7,"units":1}' \
     http://localhost:8080/rentals
sleep 2	# Fa pausa per tenir diferent temps

#Crea el quart lloguer
curl -i -H "Content-Type: application/json" \
     -d '{"maker":"Tesla","model":"Model 3","days":2,"units":3}' \
     http://localhost:8080/rentals
sleep 2	# Fa pausa per tenir diferent temps

#Crea el cinquè lloguer
curl -i -H "Content-Type: application/json" \
     -d '{"maker":"Renault","model":"Clio","days":10,"units":1}' \
     http://localhost:8080/rentals

echo Finished!

