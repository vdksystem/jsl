package tech.adimen


@Singleton
class Pipeline implements Serializable {
    private Map config = [:]

    private Map postSteps = [:]

    private Map postStepsErrors = [:]

    public init(pipelineConfig = null) {
        println("Initializing base class")
        if( pipelineConfig in Map ) this.config = pipelineConfig
        this
    }

    public String getAgentLabel() {
        config.agent_label
    }

    public void postStep(String name, Closure body) {
        if( ! postSteps[name] ) postSteps[name] = []
        postSteps[name] << body
    }

    public void postStepsRun(String name = 'always') {
        if( postSteps[name] ) {
            for( def i = postSteps[name].size()-1; i >= 0 ; i-- ) {
                try {
                    postSteps[name][i]()
                }
                catch( ex ) {
                    postStepError(name, postSteps[name][i], ex)
                }
            }
        }
    }

    public void postStepError(String name, Exception exception) {
        if( ! postStepsErrors[name] ) postStepsErrors[name] = []
        postStepsErrors[name] << exception
    }

    public List getPostStepsErrors(String name) {
        postStepsErrors[name] ?: []
    }

    public Map getConfig(String name) {
        config[name] ?: [:]
    }

}
