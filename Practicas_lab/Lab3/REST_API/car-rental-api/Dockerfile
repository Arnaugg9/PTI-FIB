FROM node:18

# Create app directory
WORKDIR /usr/src/app

# Install app dependencies
COPY package*.json ./

RUN npm install

# Bundle app source	
COPY server.js .
COPY rentals.json .

CMD [ "node", "server.js" ]

