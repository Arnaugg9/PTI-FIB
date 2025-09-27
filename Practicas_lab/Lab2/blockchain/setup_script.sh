#Run the two nodes
pkill python
python3 blockchain.py -p 5000 &
python3 blockchain.py -p 5001 &

sleep 3

#Write two transactions into node 1
curl -X POST -H "Content-Type: application/json" -d '{"sender": "A","recipient": "B", "amount": 8, "order": 1  }' http://localhost:5000/transactions/new
curl -X POST -H "Content-Type: application/json" -d '{"sender": "B","recipient": "C", "amount": 5, "order": 2  }' http://localhost:5000/transactions/new

#Mine block at node 1 
curl http://localhost:5000/mine

#See chain at node 1 
curl http://localhost:5000/chain

#Mine another block at node 1 
curl http://localhost:5000/mine

#See chain at node 1 
curl http://localhost:5000/chain

#Mine another block at node 1 
curl http://localhost:5000/mine

#See chain at node 1 
curl http://localhost:5000/chain

# At this point, node 1 should have three blocks.
# We will operate now on node 2
#Mine block at node 2 
curl http://localhost:5001/mine

#Mine another block at node 2
curl http://localhost:5001/mine

#See chain at node 2 
curl http://localhost:5001/chain

