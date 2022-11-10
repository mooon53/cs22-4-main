import requests

def send_whatsapp(x):
    headers = {
        'Authorization': 'Bearer EAAJErtnssFEBAAnkzuxy1JOZA6KdXDnmbWn1ruPeqZCoSkeYy9ZAFdhBvjWHZBX0Efv7XsThgpOtcfB9WaR9Uah8mW09yJu4u1tRBdAHQOUDZC25sSidCgRQ5wgObvsPZA6lFVBaCiMY1tnnKkEfQRA8z2L5KtCA5eUACzDq3ZB5F8HjqhWvcmXIc9MDZBBOLZCAbFZBZA9ac3fyJ8xOtrrA1f4',
        # Already added when you pass json= but not when you pass data=
        # 'Content-Type': 'application/json',
    }

    json_data = {
        'messaging_product': 'whatsapp',
        'to': x , #Current Phone number
        'type': 'template',
        'template': {
            'name': 'pisec',
            'language': {
                'code': 'en_US',
            },
        },
    }

    response = requests.post('https://graph.facebook.com/v14.0/105193312386949/messages', headers=headers, json=json_data)
    print("Whatsapp Message sent to!")

    # Note: json_data will not be serialized by requests
    # exactly as it was in the original request.
    #data = '{ "messaging_product": "whatsapp", "to": "31623171402", "type": "template", "template": { "name": "alert", "language": { "code": "en_US" } } }'
    #response = requests.post('https://graph.facebook.com/v14.0/105193312386949/messages', headers=headers, data=data)

