package org.gooru.nucleus.handlers.lookup.processors.commands;

import org.gooru.nucleus.handlers.lookup.processors.ProcessorContext;
import org.gooru.nucleus.handlers.lookup.processors.repositories.RepoBuilder;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;

/**
 * @author ashish on 2/1/17.
 */
class SchoolsProcessor extends AbstractCommandProcessor {
    public SchoolsProcessor(ProcessorContext context) {
        super(context);
    }

    @Override
    protected void setDeprecatedVersions() {

    }

    @Override
    protected MessageResponse processCommand() {
        return RepoBuilder.buildSchoolsRepo()
            .getSchools(context.keyword(), context.schoolDistrictId(), context.limit(), context.offset());
    }
}
