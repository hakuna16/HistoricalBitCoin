const urlEnvironmentMapping = {
  dev: {
    apiUrl: '',
  },
  ppe: {
    apiUrl: '',
  },
  prod: {
    apiUrl: '',
  },
  local: {
    apiUrl: 'http://localhost:8080',
  },
};

/* A function that returns a function that returns an object. */
export default ((env) => {
  return env ? urlEnvironmentMapping[env] : urlEnvironmentMapping.local;
})('local');
