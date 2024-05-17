SUMMARY = "OpenMAX Integration layer for VCU"
DESCRIPTION = "OMX IL Libraries,test applications and headers for VCU"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=ef69c2bb405668101824f0b644631e2e"

# Recipe has been renamed
PROVIDES += "libomxil-xlnx"

# Version from core/core_version.mk
PV .= "+git"

BRANCH ?= "xlnx_rel_v2023.2"
REPO   ?= "git://github.com/Xilinx/vcu-omx-il.git;protocol=https"
SRCREV = "3a04b5adc661a0eced626c1373dbbfe699ae6fe0"

BRANCHARG = "${@['nobranch=1', 'branch=${BRANCH}'][d.getVar('BRANCH', True) != '']}"
SRC_URI = "${REPO};${BRANCHARG}"

SRC_URI += "file://0001-Current-gcc-requires-cstdint-for-C-types.patch"

S  = "${WORKDIR}/git"

inherit features_check

REQUIRED_MACHINE_FEATURES = "vcu"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "libvcu-ctrlsw"
RDEPENDS:${PN} = "kernel-module-vcu libvcu-ctrlsw"

EXTERNAL_INCLUDE="${STAGING_INCDIR}/vcu-ctrl-sw/include"

EXTRA_OEMAKE = " \
    CC='${CC}' CXX='${CXX} ${CXXFLAGS}' \
    EXTERNAL_INCLUDE='${EXTERNAL_INCLUDE}' \
    "

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${includedir}/vcu-omx-il

    install -m 0644 ${S}/omx_header/*.h ${D}${includedir}/vcu-omx-il

    oe_runmake install INSTALL_PATH=${D}${bindir}

    oe_libinstall -C ${S}/bin/ -so libOMX.allegro.core ${D}/${libdir}/
    oe_libinstall -C ${S}/bin/ -so libOMX.allegro.video_decoder ${D}/${libdir}/
    oe_libinstall -C ${S}/bin/ -so libOMX.allegro.video_encoder ${D}/${libdir}/
}

# These libraries shouldn't get installed in world builds unless something
# explicitly depends upon them.

EXCLUDE_FROM_WORLD = "1"
