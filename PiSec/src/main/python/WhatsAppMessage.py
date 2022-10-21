import requests

def send_whatsapp(x):
    headers = {
        'Authorization': 'Bearer EAAJErtnssFEBALswynhzJgpRFvWSb7Yy8KUPkmf10UCNImEwHik92fBgGqv8DRvdfMachtraY7IILUnHk0QAIVYyp04VHuHUMSLeFuZBeiVo0FETNv8FEM3pxD3KVyOXixN06FcXZApyOoxYtL9MPba8EqqZBIHRQoWygNdzV4thZC4qwcifZCw58FgzisqIr8GvO8IRB3Oqs73U0iDSP',
        # Already added when you pass json= but not when you pass data=
        # 'Content-Type': 'application/json',
    }

    json_data = {
        'messaging_product': 'whatsapp',
        'to': x, #Current Phone number
        'type': 'template',
        'template': {
            'name': 'alert',
            'language': {
                'code': 'en_US',
            },
        },
    }

    response = requests.post('https://graph.facebook.com/v14.0/105193312386949/messages', headers=headers, json=json_data)
    print(f"Whatsapp Message sent to!")

    # Note: json_data will not be serialized by requests
    # exactly as it was in the original request.
    #data = '{ "messaging_product": "whatsapp", "to": "31623171402", "type": "template", "template": { "name": "alert", "language": { "code": "en_US" } } }'
    #response = requests.post('https://graph.facebook.com/v14.0/105193312386949/messages', headers=headers, data=data)

#send_whatsapp(31623171402)