LINUX_VERSION = "6.1.0"
YOCTO_META ?= "git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-6.1;destsuffix=yocto-kmeta"
KBRANCH="master"
SRCREV = "bdf00302ed29141c201e9c05b7338db698b2dce9"
SRCREV_meta = "185bcfcbe480c742247d9117011794c69682914f"

KCONF_AUDIT_LEVEL="0"

include linux-xlnx.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
