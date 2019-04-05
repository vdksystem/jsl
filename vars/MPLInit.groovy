def call() {
  // Using the MPL library and adding the custom path to find modules
  // MPL library should be added to Jenkins otherwise it will fail
  library('mpl')
  MPLModulesPath('tech/vdk/')
}
