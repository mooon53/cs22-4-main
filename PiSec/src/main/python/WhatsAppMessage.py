import requests

def send_whatsapp(x):
    headers = {
        'Authorization': 'Bearer EAAJErtnssFEBANxpkDgPSaABCZCGgMRpuVclZBloAGCbLnnq9t78ARTYmtMxZBa1scC3oQV3CFzev0hoVGzs7ZBiQX2Bcca2muW982pZBA3wxy3jQ35Y4tVlsWR1EpzmvYZCaTWK8z8iZBX0SgZBovR42ZBgU2rg7prXdf2HdqlkcZABIhcdfOermkIB4ZCpIgZC6MROUyalYk6sFRyp3EEBwRYL',
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

