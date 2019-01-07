package org.gooru.nucleus.handlers.lookup.processors.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.gooru.nucleus.handlers.lookup.constants.MessageConstants;
import org.gooru.nucleus.handlers.lookup.processors.Processor;
import org.gooru.nucleus.handlers.lookup.processors.ProcessorContext;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ashish on 2/1/17.
 */
public enum CommandProcessorBuilder {

  DEFAULT("default") {
    private final Logger LOGGER = LoggerFactory.getLogger(CommandProcessorBuilder.class);
    private final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");

    @Override
    public Processor build(ProcessorContext context) {
      return () -> {
        LOGGER.error("Invalid operation type passed in, not able to handle");
        return MessageResponseFactory
            .createInvalidRequestResponse(RESOURCE_BUNDLE.getString("invalid.operation"));
      };
    }
  },
  ACCESS_HAZARDS(MessageConstants.MSG_OP_LKUP_ACCESS_HAZARDS) {
    @Override
    public Processor build(ProcessorContext context) {
      return new AccessHazardsProcessor(context);
    }
  },
  CEN_SKILLS(MessageConstants.MSG_OP_LKUP_21_CEN_SKILLS) {
    @Override
    public Processor build(ProcessorContext context) {
      return new Century21SkillsProcessor(context);
    }
  },
  AD_STATUS(MessageConstants.MSG_OP_LKUP_AD_STATUS) {
    @Override
    public Processor build(ProcessorContext context) {
      return new AdStatusProcessor(context);
    }
  },
  EDU_USE(MessageConstants.MSG_OP_LKUP_EDU_USE) {
    @Override
    public Processor build(ProcessorContext context) {
      return new EducationalUseProcessor(context);
    }
  },
  GRADE(MessageConstants.MSG_OP_LKUP_GRADE) {
    @Override
    public Processor build(ProcessorContext context) {
      return new GradeProcessor(context);
    }
  },
  MEDIA_FEATURES(MessageConstants.MSG_OP_LKUP_MEDIA_FEATURES) {
    @Override
    public Processor build(ProcessorContext context) {
      return new MediaFeaturesProcessor(context);
    }
  },
  READ_LEVEL(MessageConstants.MSG_OP_LKUP_READ_LEVEL) {
    @Override
    public Processor build(ProcessorContext context) {
      return new ReadingLevelsProcessor(context);
    }
  },
  DOK(MessageConstants.MSG_OP_LKUP_DOK) {
    @Override
    public Processor build(ProcessorContext context) {
      return new DepthOfKnowledgeProcessor(context);
    }
  },
  AUDIENCE(MessageConstants.MSG_OP_LKUP_AUDIENCE) {
    @Override
    public Processor build(ProcessorContext context) {
      return new AudienceProcessor(context);
    }
  },
  MOMENTS(MessageConstants.MSG_OP_LKUP_MOMENTS) {
    @Override
    public Processor build(ProcessorContext context) {
      return new MomentsOfLearningProcessor(context);
    }
  },
  COUNTRIES(MessageConstants.MSG_OP_LKUP_COUNTRIES) {
    @Override
    public Processor build(ProcessorContext context) {
      return new CountriesProcessor(context);
    }
  },
  STATES(MessageConstants.MSG_OP_LKUP_STATES) {
    @Override
    public Processor build(ProcessorContext context) {
      return new StatesProcessor(context);
    }
  },
  SCHOOLDISTRICTS(MessageConstants.MSG_OP_LKUP_SCHOOLDISTRICTS) {
    @Override
    public Processor build(ProcessorContext context) {
      return new SchoolDistrictsProcessor(context);
    }
  },
  SCHOOLS(MessageConstants.MSG_OP_LKUP_SCHOOLS) {
    @Override
    public Processor build(ProcessorContext context) {
      return new SchoolsProcessor(context);
    }
  },
  LICENSES(MessageConstants.MSG_OP_LKUP_LICENSES) {
    @Override
    public Processor build(ProcessorContext context) {
      return new LicensesProcessor(context);
    }
  },
  APIKEY_CONFIG(MessageConstants.MSG_OP_LKUP_APIKEY_CONFIG) {
    @Override
    public Processor build(ProcessorContext context) {
      return new ApiKeyConfigProcessor(context);
    }
  },
  FIREBASE_JWT_CREATION(MessageConstants.MSG_OP_FIREBASE_JWT_CREATION) {
    @Override
    public Processor build(ProcessorContext context) {
      return new FirebaseJwtCreationProcessor(context);
    }
  },
  LANGUAGES(MessageConstants.MSG_OP_LKUP_LANGUAGES) {
    @Override
    public Processor build(ProcessorContext context) {
      return new LanguagesProcessor(context);
    }
  };

  private String name;

  CommandProcessorBuilder(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  private static final Map<String, CommandProcessorBuilder> LOOKUP = new HashMap<>();

  static {
    for (CommandProcessorBuilder builder : values()) {
      LOOKUP.put(builder.getName(), builder);
    }
  }

  public static CommandProcessorBuilder lookupBuilder(String name) {
    CommandProcessorBuilder builder = LOOKUP.get(name);
    if (builder == null) {
      return DEFAULT;
    }
    return builder;
  }

  public abstract Processor build(ProcessorContext context);
}
