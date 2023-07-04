/* eslint-disable no-console */
const express = require('express');
const path = require('path');

const basePath = '/';

const app = express();
const port = process.env.PORT || 3000;

app.listen(port, () => console.log(`Listening on port ${port}`));

app.get('*.js', (req, res, next) => {
  req.url += '.gz';
  res.set('Content-Encoding', 'gzip');
  next();
});

app.use(express.static(path.join(__dirname, '../build')));

app.get(`${basePath}`, (req, res) => {
  res.sendFile(path.join(__dirname, '../build', 'index.html'));
});

app.get(`${basePath}/health`, (request, response) => {
  const healthCheck = {};
  healthCheck.APP_ENV = process.env.APP_ENV;
  healthCheck.BUILD_NUMBER = process.env.BUILD_NUMBER;
  response.json(healthCheck);
});

console.log(`serving react app from port ${port} for production build from build folder`);
