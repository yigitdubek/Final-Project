import requests

from ultralytics import YOLO
import cv2
import numpy as np

from util import read_license_plate

# Load models
coco_model = YOLO('yolov8n.pt')  # Yolov8 model for general object detection including vehicles
license_plate_detector = YOLO(
    'license_plate_detector.pt')  # Specialized model for license plate detection

# Load a single frame from an image or video
frame = cv2.imread('./Image/motorbike1.jpg')  # Replace with the correct path to your frame
# Specify vehicle class IDs from the COCO dataset that represent vehicles
vehicles = [2, 3, 5, 7]  # e.g., cars, motorcycles, trucks, buses
classNames = ["person", "bicycle", "car", "motorbike", "aeroplane", "bus", "train", "truck", "boat",
              "traffic light", "fire hydrant", "stop sign", "parking meter", "bench", "bird", "cat",
              "dog", "horse", "sheep", "cow", "elephant", "bear", "zebra", "giraffe", "backpack", "umbrella",
              "handbag", "tie", "suitcase", "frisbee", "skis", "snowboard", "sports ball", "kite", "baseball bat",
              "baseball glove", "skateboard", "surfboard", "tennis racket", "bottle", "wine glass", "cup",
              "fork", "knife", "spoon", "bowl", "banana", "apple", "sandwich", "orange", "broccoli",
              "carrot", "hot dog", "pizza", "donut", "cake", "chair", "sofa", "pottedplant", "bed",
              "diningtable", "toilet", "tvmonitor", "laptop", "mouse", "remote", "keyboard", "cell phone",
              "microwave", "oven", "toaster", "sink", "refrigerator", "book", "clock", "vase", "scissors",
              "teddy bear", "hair drier", "toothbrush"
              ]
# Detect objects in the frame
detections = coco_model(frame)[0]

# Initialize results dictionary
results = {}
# Iterate over detections and process only vehicles
for detection in detections.boxes.data.tolist():
    x1, y1, x2, y2, score, class_id = detection
    if int(class_id) in vehicles:
        # Crop vehicle from the frame
        value_score=int(100*score)
        if(value_score> 40):
            vehicle_crop = frame[int(y1):int(y2), int(x1):int(x2)]
            cv2.imshow('Cropped Vehicle', vehicle_crop)
            cv2.waitKey(100)
            # Detect license plates within the cropped vehicle area
            license_plates = license_plate_detector(vehicle_crop)[0]

            for lp in license_plates.boxes.data.tolist():
                lp_x1, lp_y1, lp_x2, lp_y2, lp_score, lp_class_id = lp
                license_plate_image = vehicle_crop[int(lp_y1):int(lp_y2), int(lp_x1):int(lp_x2)]
                license_plate_crop_gray = cv2.cvtColor(license_plate_image, cv2.COLOR_BGR2GRAY)
                _, license_plate_crop_thresh = cv2.threshold(license_plate_crop_gray, 0, 255, cv2.THRESH_BINARY + cv2.THRESH_OTSU)
                cv2.imshow('Cropped Vehicle', license_plate_crop_thresh)
                cv2.waitKey(2000)
                license_plate_text  = read_license_plate(license_plate_crop_thresh)

                if license_plate_text is not None:
                        results = {'vehicles': classNames[int(class_id)],
                                    'license_plate':license_plate_text}
                        data = {'vehicles': results}
                        response = requests.post('https://httpbin.org/post', json=data)
                        if response.status_code==200:
                                print(data)
                                print('Başarıyla gönderildi.')
                        else:
                                print('Bir hata oluştu',response.status_code)



# Output the results
