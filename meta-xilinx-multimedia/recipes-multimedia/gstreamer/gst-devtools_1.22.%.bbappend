require gstreamer-xilinx-1.22.%.inc

SRC_URI:append = " \
    file://0001-connect-has-a-different-signature-on-musl.patch \
"

S = "${UNPACKDIR}/git/subprojects/gst-devtools"
