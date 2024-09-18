import string

import cv2
import easyocr
import numpy as np

# Initialize the OCR reader
reader = easyocr.Reader(['en'], gpu=False )


number = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'U', 'V', 'Y', 'Z']

def license_complies_format(text):
    print(text)
    print(len(text))

    if len(text) <= 6 or len(text) >=9:
        return False

    if (text[0] in number) and \
       (text[1] in number) and \
       (text[2] in alphabet) and \
       (text[(len(text))-2] in number) and \
       (text[len(text)-1] in number):
        return True
    else:
        return False


def read_license_plate(license_plate_crop):
    license_plate_string = ""
    detections = reader.readtext(license_plate_crop, allowlist = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ')
    if not detections:
        return None, None

    for detection in detections:
        bbox, text, score = detection
        # Check if text is not empty before concatenating
        if text:
            license_plate_string += text
            print(score)
    filtered_text = license_plate_string.replace(" ", "").replace("{", "").replace("}", "")

    # Check if the concatenated string is not empty and complies with format
    if license_complies_format(filtered_text):
        print(filtered_text)
        print(score)
        return filtered_text, score

    return None, None

def get_vehicleType(license_plate,detection_):
    x1, y1, x2, y2, score, class_id2 = license_plate
    vehicleType=None
    found_it = False
    number =0

    for j in range(len(detection_)):
        xcar1, ycar1, xcar2, ycar2, score,class_id = detection_[j]

        if x1 > xcar1 and y1 > ycar1 and x2 < xcar2 and y2 < ycar2:
            found_it = True
            number=j
            break

    if found_it:
        return detection_[j]

    return -1, -1, -1, -1, -1,-1;



