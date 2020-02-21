CHART_REPO := $(or $(CHART_REPO),jenkins-x-chartmuseum:8080)
CURRENT=$(shell pwd)
NAME := activiti-cloud-audit
ORG := $(or $(ORG),activiti)
APP_NAME := $(or $(APP_NAME),$(NAME))
OS := $(shell uname)
RELEASE_VERSION := $(or $(VERSION),$(shell cat ../../VERSION))
RELEASE_ARTIFACT := $(or $(RELEASE_ARTIFACT),$(APP_NAME))

GITHUB_CHARTS_REPO := $(or $(GITHUB_CHARTS_REPO),$(shell git config --get remote.origin.url))
GITHUB_CHARTS_BRANCH := $(or $(GITHUB_CHARTS_BRANCH),gh-pages)
GITHUB_CHARTS_DIR:= $(or $(GITHUB_CHARTS_DIR),activiti-cloud-helm-charts)

build: clean
	helm init --client-only
	helm repo add charts_activiti https://activiti.github.io/activiti-cloud-charts/
	helm repo add activiti-cloud-helm-charts https://activiti.github.io/activiti-cloud-helm-charts/
	helm dependency build
	helm lint

dry-run: 
	helm upgrade ${HELM_RELEASE} . --install --debug --dry-run

$(NAME)-$(RELEASE_VERSION).tgz: build
	helm package .

package: $(NAME)-$(RELEASE_VERSION).tgz

preview: version package dry-run release

install: build dry-run
	helm install . --name ${NAME}

upgrade: clean build
	helm upgrade ${NAME} .

delete:
	helm delete --purge ${NAME}

clean:
	rm -rf requirements.lock
	rm -rf charts
	rm -rf ${NAME}*.tgz

release: package
	curl  -u $(CHARTMUSEUM_CREDS_USR):$(CHARTMUSEUM_CREDS_PSW) --data-binary "@$(NAME)-$(RELEASE_VERSION).tgz" $(CHART_REPO)/api/charts
	rm -rf ${NAME}*.tgz%

push:
	for i in {0..20}; do \
	   	cd $(GITHUB_CHARTS_DIR) && \
	   	git push origin "$(GITHUB_CHARTS_BRANCH)"; \
	   	[[ $$? == 0 ]]&&exit 0; \
	   	echo git push try $$i; \
	done

github:
	$(eval GITHUB_CHARTS_DIR := $(shell basename $(GITHUB_CHARTS_REPO) .git))
	[[ -d $(GITHUB_CHARTS_DIR) ]] ||git clone -b "$(GITHUB_CHARTS_BRANCH)" "$(GITHUB_CHARTS_REPO)" $(GITHUB_CHARTS_DIR)
	cp "$(NAME)-$(RELEASE_VERSION).tgz" $(GITHUB_CHARTS_DIR)
	cd $(GITHUB_CHARTS_DIR) && \
	   helm repo index . && \
	   git add . && \
	   git status && \
	   git commit -m "fix:(version) release $(NAME)-$(RELEASE_VERSION).tgz" && \
	   git pull ;
	$(MAKE) push
	rm -rf $(GITHUB_CHARTS_DIR)

version: 
	sed -i -e "s/version:.*/version: $(VERSION)/" Chart.yaml
	sed -i -e "s|repository: .*|repository: $(DOCKER_REGISTRY)/$(ORG)/$(NAME)|" values.yaml
	sed -i -e "s/tag: .*/tag: $(VERSION)/" values.yaml

tag: version
	git add --all
	git commit -m "release $(RELEASE_VERSION)" --allow-empty # if first release then no verion update is performed
	git tag -fa v$(RELEASE_VERSION) -m "Release version $(RELEASE_VERSION)"
	git push origin v$(RELEASE_VERSION)

updatebot/push-version: 
	@echo Resolving push versions for artifacts........
	$(eval ACTIVITI_CLOUD_AUDIT_SERVICE_VERSION=$(shell mvn help:evaluate -Dexpression=activiti-cloud-audit-service.version -q -DforceStdout -f ../../pom.xml))
	$(eval MAVEN_VERSIONS=org.activiti.cloud.examples:activiti-cloud-audit $(RELEASE_VERSION) org.activiti.cloud.audit:activiti-cloud-audit-dependencies $(ACTIVITI_CLOUD_AUDIT_SERVICE_VERSION))
	
	@echo Doing updatebot push-version.....
	for number in 1 2; do \
		updatebot -d ../../ push-version --kind maven $(MAVEN_VERSIONS); \
		rm -rf ../../.updatebot-repos/; \
		sleep $$[ ( $$RANDOM % 10 )  + 1 ]s; \
	done

