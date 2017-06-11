#!/usr/bin/env bash
sudo yum -y update  &&
sudo yum -y install yum-utils device-mapper-persistent-data lvm2 &&
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo &&
sudo yum -y makecache fast &&
sudo yum -y install docker-ce &&

if [ ! -e /etc/docker/daemon.json ]; then
  sudo mkdir /etc/docker &&
  echo -e "{\n\t\"storage-driver\": \"devicemapper\"\n}" | sudo tee -a /etc/docker/daemon.json
fi

sudo systemctl enable docker &&
sudo systemctl start docker