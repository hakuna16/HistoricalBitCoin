module.exports = {
  mode: 'development',
  devtool: 'cheap-module-source-map',
  devServer: {
    open: true,
    compress: true,
    allowedHosts: ['localhost'],
    bonjour: true,
    host: 'localhost',
    port: '3000',
    liveReload: true,
    hot: true,
    client: {
      logging: 'info',
      overlay: {
        errors: true,
        warnings: false,
      },
      progress: true,
    },
    historyApiFallback: {
      disableDotRule: true,
    },
  },
};
