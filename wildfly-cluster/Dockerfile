FROM jboss/wildfly:17.0.1.Final
ENV JBOSS_CONFIG standalone-full-ha.xml
ARG NODE
ARG NODE_NAME

RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent
ADD customization /opt/jboss/wildfly/customization

RUN /opt/jboss/wildfly/customization/execute.sh standalone $JBOSS_CONFIG $NODE_NAME

RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/current
CMD  /opt/jboss/wildfly/bin/standalone.sh -c $JBOSS_CONFIG -b 0.0.0.0 -bmanagement 0.0.0.0 -u 230.0.0.4 $NODE