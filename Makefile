ifneq ("$(wildcard .env)","")
	include .env
	export
endif

define upload
	rsync -avz \
		--checksum \
		--rsh="ssh -o StrictHostKeyChecking=no" \
		$(1) \
		${DEPLOY_HOST}:$(DEPLOY_PATH)/$(patsubst %,%,$(2))
endef

define command
	ssh -o StrictHostKeyChecking=no $(DEPLOY_HOST) $(1)
endef

server=anunaki
.PHONY: test upload
test:
	$(call command, "supervisorctl status anunaki ")

upload:
	$(call command, "supervisorctl stop anunaki")
	$(call command, "mkdir -p $(DEPLOY_PATH)")
	$(call upload, jeecg-module-system/jeecg-system-start/target/jeecg-system-start-3.4.4.jar, anunaki-3.4.4.jar)
	$(call upload, jeecg-module-system/jeecg-system-start/target/lib/, lib/)
	$(call upload, jeecg-module-system/jeecg-system-start/target/config/, config/)
	$(call upload, script/*, ./)
	#$(call upload, .env.pro, .env)
	$(call command, "chmod +x ./*.sh && \
			supervisorctl start $(server) &&\
			supervisorctl status $(server)  | head &&\
			sleep 3 && \
			supervisorctl tail $(server) | cat \
		")

