FROM node:16-alpine

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm install --silent
RUN npm install react-scripts@3.4.1 -g --silent

COPY src ./

EXPOSE 3000

CMD ["npm", "start"]
