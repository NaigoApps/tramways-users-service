#%1: Version

npm install @openapitools/openapi-generator-cli -g
npm update -g @openapitools/openapi-generator-cli
rm -r ./"$1"/generated

openapi-generator-cli generate \
-g spring \
-c ./"$1"/spring-conf.yaml \
-i ./"$1"/users-api.yaml \
-o ./"$1"/generated/spring \
--api-package it.tramways.users.api."$1" \
--model-package it.tramways.users.api."$1".model \
--group-id it.tramways \
--artifact-id users-service-server-api \
--artifact-version="$2"

openapi-generator-cli generate \
-g java \
-i ./"$1"/users-api.yaml \
-o ./"$1"/generated/java \
--api-package it.tramways.users.api."$1" \
--model-package it.tramways.users.api."$1".model \
--group-id it.tramways \
--artifact-id users-service-client-api \
--artifact-version="$2"

openapi-generator-cli generate \
-g typescript-axios \
-c ./"$1"/javascript-conf.yaml \
-i ./"$1"/users-api.yaml \
-o ./"$1"/generated/javascript \
--api-package it.tramways.users.api."$1" \
--model-package it.tramways.users.api."$1".model \
--artifact-version="$2"
